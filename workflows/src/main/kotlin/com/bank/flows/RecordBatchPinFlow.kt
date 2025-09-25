package com.bank.flows

import com.bank.BatchPinContract
import com.bank.BatchPinState
import net.corda.v5.application.flows.*
import net.corda.v5.base.annotations.Suspendable
import net.corda.v5.application.identity.Party
import net.corda.v5.ledger.transactions.TransactionBuilder
import net.corda.v5.ledger.contracts.Command

@InitiatingFlow
@StartableByRPC
class RecordBatchPinFlow(
    private val author: String,
    private val batchHash: String,
    private val transactionHash: String,
    private val blockHash: String,
    private val blockNumber: String,
    private val payloadRef: String,
    private val namespace: String,
    private val timestamp: Long
) : Flow<Unit> {

    @Suspendable
    override fun call() {
        val notary = serviceHub.networkMapCache.notaryIdentities.first()
        val outputState = BatchPinState(
            author,
            batchHash,
            transactionHash,
            blockHash,
            blockNumber,
            payloadRef,
            namespace,
            timestamp,
            listOf(ourIdentity)
        )
        val command = Command(BatchPinContract.Commands.Record(), ourIdentity.owningKey)

        val txBuilder = TransactionBuilder(notary)
            .addOutputState(outputState, BatchPinContract.ID)
            .addCommand(command)

        txBuilder.verify(serviceHub)
        val signedTx = serviceHub.signInitialTransaction(txBuilder)

        subFlow(FinalityFlow(signedTx, emptyList()))
    }
}

package com.bank

import net.corda.v5.ledger.contracts.BelongsToContract
import net.corda.v5.ledger.contracts.LinearState
import net.corda.v5.ledger.contracts.UniqueIdentifier
import net.corda.v5.application.identity.Party

@BelongsToContract(BatchPinContract::class)
data class BatchPinState(
    val author: String,
    val batchHash: String,
    val transactionHash: String,
    val blockHash: String,
    val blockNumber: String,
    val payloadRef: String,
    val namespace: String,
    val timestamp: Long,
    val participantsList: List<Party>,
    override val linearId: UniqueIdentifier = UniqueIdentifier()
) : LinearState {
    override val participants = participantsList
}

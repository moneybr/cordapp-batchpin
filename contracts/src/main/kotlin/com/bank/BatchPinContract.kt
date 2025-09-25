package com.bank

import net.corda.v5.ledger.contracts.Contract
import net.corda.v5.ledger.contracts.CommandData
import net.corda.v5.ledger.contracts.LedgerTransaction

class BatchPinContract : Contract {
    companion object {
        const val ID = "com.bank.BatchPinContract"
    }

    override fun verify(tx: LedgerTransaction) {
        val command = tx.commandsOfType<Commands>().single()
        when (command.value) {
            is Commands.Record -> {
                require(tx.outputStates.size == 1) { "Debe existir un único BatchPinState de salida" }
            }
        }
    }

    interface Commands : CommandData {
        class Record : Commands
    }
}

package com.bank.cordapp.contracts

import net.corda.core.contracts.CommandData
import net.corda.core.contracts.Contract
import net.corda.core.contracts.TypeOnlyCommandData
import net.corda.core.transactions.LedgerTransaction

class BankProofContract : Contract {
    companion object {
        const val ID = "com.bank.cordapp.contracts.BankProofContract"
    }
    override fun verify(tx: LedgerTransaction) {
        // Demo: sin reglas de verificación (no se usan states en este minimal)
    }
    interface Commands : CommandData {
        class Issue : TypeOnlyCommandData(), Commands
    }
}

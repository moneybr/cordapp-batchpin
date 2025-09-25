package com.bank.cordapp.flows

import co.paralleluniverse.fibers.Suspendable
import net.corda.core.flows.FlowLogic
import net.corda.core.flows.InitiatingFlow
import net.corda.core.flows.StartableByRPC

@InitiatingFlow
@StartableByRPC
class IssueProofFlow(
    private val proofId: String,
    private val amount: String,
    private val currency: String
) : FlowLogic<String>() {
    @Suspendable
    override fun call(): String {
        // Minimal demo: no crea estados, solo retorna texto para validar RPC
        return "BankProof creado con id=" + proofId + " amount=" + amount + " currency=" + currency
    }
}

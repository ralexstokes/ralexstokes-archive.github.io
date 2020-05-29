---
layout: post
title: "Layer 2 insurance"
date: 2020-05-28 20:01 -0700
---

Optimistic rollups have gained a lot of popularity recently as they give us higher throughput today without the constraints of rollups powered by zero-knowledge proofs ("zk-rollups"). These constraints come about as the underlying cryptography is too expensive for many algorithms we want to run in a L2 system today (e.g. the entire EVM). Optimistic rollups forgo the safety guarantees of the cryptography by replacing this security margin with economic incentives. The tradeoff is sound in practice but does result in higher latency as an exit from the system has to wait "in limbo" to give a challenger time to raise the alarm on a possibly malicious L2 operator. If the operator is found to have faulted, they lose some (or all!) of their capital at risk and everyone with a position inside the L2 is given a chance to safely exit.

I've been thinking a lot about DeFi recently and we can apply some concepts there to make the situation around optimistic rollups a bit better by insuring default risk of the operator while expediting the withdrawal of an individual user. The individual can pay a fee to a pool of capital (think Uniswap's liquidity providers) who assume the risk in the chance of operator misbehavior. This pool of capital in effect operates like insurance for the L2 and in the mean time greatly enhancing the user experience. And thus, L2 insurance was born.

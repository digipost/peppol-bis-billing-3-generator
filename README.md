[![Maven Central](https://maven-badges.herokuapp.com/maven-central/no.digipost/peppol-bis-invoice-3-generator/badge.svg)](https://maven-badges.herokuapp.com/maven-central/no.digipost/peppol-bis-invoice-3-generator)
![](https://github.com/digipost/digipost-micrometer-prometheus/workflows/Build%20and%20deploy/badge.svg)
[![License](https://img.shields.io/badge/license-Apache%202-blue)](https://github.com/digipost/peppol-bis-invoice-3-generator/blob/master/LICENCE)

# Peppol bis billing 3 generator

This is a Pojo implementation of https://docs.peppol.eu/poacc/billing/3.0/. For now
only Invoice is created.

The principle is that the domain classes in plain java encode naming and cardinality
and they can produce xml from itself. 

We use `eaxy` as an XML generator because of
it ease of use and no dependency nature. 


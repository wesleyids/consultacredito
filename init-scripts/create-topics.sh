#!/bin/bash
kafka-topics.sh --create --topic topic_eicon_creditoconsulta_log --bootstrap-server kafka:9092 --partitions 1 --replication-factor 1
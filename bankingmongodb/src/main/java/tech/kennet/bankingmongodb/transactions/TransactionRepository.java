package tech.kennet.bankingmongodb.transactions;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String>{
    
}

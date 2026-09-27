package banking.observer;
import java.time.LocalDateTime;
public record AccountEvent(String accountId, String holder, TransactionType type,double amount, double balanceAfter, LocalDateTime at){
    public boolean isCredit(){
        return type == TransactionType.DEPOSIT || type == TransactionType.TRANSFER_IN;
    }
    public String describe(){
        return String.format("%s %s %.2f -> balance %.2f",accountId,type, amount, balanceAfter);
    }
    
}
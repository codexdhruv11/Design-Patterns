package banking.observer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Account implements Subject{
    private final List<Observer> observers = new ArrayList<>();
    private final String id;
    private final String holder;
    private double balance;
    public Account(String id , String holder, double opening){
        this.id = id;
        this.holder = holder;
        this.balance = opening;
    }
    @Override
    public void registerObserver(Observer observer){
        if(!observers.contains(observer)) observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    @Override
    public void notifyObservers(AccountEvent event){
        for(Observer o : new ArrayList<>(observers)){
            o.update(event);
        }
    }
    public void deposit(double amount){
        apply(TransactionType.DEPOSIT, amount);
    }
    public void withdraw(double amount){
        if(amount > balance){
            throw new IllegalArgumentException("Insufficient funds in" + id);
        }
        apply(TransactionType.WITHDRAWAL, amount);
    }
    void apply(TransactionType type, double amount){
        if(amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        balance += (type == TransactionType.DEPOSIT || type == TransactionType.TRANSFER_IN) ? amount : -amount;
        notifyObservers(new AccountEvent(id, holder, type, amount,balance, LocalDateTime.now()));
    }
    public String getId(){return id;}
    public String getHolder(){return holder;}
    public double getBalance(){return balance;}
    public int observerCount(){return observers.size();}
    
}
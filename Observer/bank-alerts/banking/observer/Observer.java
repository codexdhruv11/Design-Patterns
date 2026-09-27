package banking.observer;
public interface Observer{
    void update(AccountEvent event);
    String name();
    
}
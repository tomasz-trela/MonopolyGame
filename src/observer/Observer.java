package observer;

public interface Observer {
    double rateModifier = 0.05; // TODO o tyle będzie rósł kurs wydanej waluty (DO MODYFIKACJI)

    // Dwie wersje update() dla łatwiejszej implementacji
    void updateDollar();
    void updateEuro();


} 

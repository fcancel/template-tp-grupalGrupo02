package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.client.input.supplier.ClientSupplier;
import ar.fiuba.tdd.tp.client.output.consumer.ClientConsumer;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ClientTest {

    private Client client;
    private ClientSupplier supplier;
    private ClientConsumer consumer;

    @Before
    public void setUp() throws Exception {
        this.supplier = mock(ClientSupplier.class);
        this.consumer = mock(ClientConsumer.class);

//        this.client = new Client(supplier, consumer);
    }

    @Test
    public void testRun() throws Exception {
//        when(this.supplier.getRequest()).thenReturn(new ClientRequest("exit"));
//
//        this.client.run();
//
//        Mockito.verify(this.supplier).getRequest();
//        Mockito.verify(this.consumer).consume(Constants.WELCOME);
//        Mockito.verify(this.consumer).consume(Constants.CLOSE_SUCCESSFUL);
//
//        Mockito.verifyNoMoreInteractions(this.consumer, this.supplier);

//        Assert.assertFalse(this.client.isRunning());
    }

}
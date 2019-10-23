public class Plan1571774015449 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
DecreaseTraffic("A");

StartServer("B");

} else {

}

StartServer("A");

}

}
}

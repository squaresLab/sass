public class Plan1571768751518 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

}

StartServer("A");


}

StartServer("A");

StartServer("A");

}
}

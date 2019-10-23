public class Plan1571773320495 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
DecreaseTraffic("A");
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}


}

StartServer("B");

}
}

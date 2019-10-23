public class Plan1571768310323 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

} else {
StartServer("B");
}

StartServer("B");

}

}
}

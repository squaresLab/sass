public class Plan1571768061589 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

} else {
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}

}


}

DecreaseTraffic("A");

}
}

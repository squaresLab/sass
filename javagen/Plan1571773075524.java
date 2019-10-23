public class Plan1571773075524 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("C");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}

}


}

DecreaseTraffic("A");
StartServer("A");

StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}




}
}

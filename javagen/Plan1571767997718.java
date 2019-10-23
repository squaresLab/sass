public class Plan1571767997718 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("B");
StartServer("A");

}

DecreaseTraffic("A");



StartServer("C");
StartServer("A");


}

}
}

public class Plan1571774464664 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

} else {

}


StartServer("C");
StartServer("B");

DecreaseTraffic("A");


}

}
}

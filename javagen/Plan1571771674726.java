public class Plan1571771674726 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}

DecreaseTraffic("A");

}


}
}

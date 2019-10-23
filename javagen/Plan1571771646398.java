public class Plan1571771646398 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

if ( StartServer("C") ) {
if ( StartServer("B") ) {
StartServer("A");
} else {
DecreaseTraffic("B");
}

} else {

}


}

}
}

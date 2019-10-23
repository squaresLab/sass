public class Plan1571773089724 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("B");
} else {
DecreaseTraffic("A");
}

StartServer("C");

} else {
StartServer("C");
}


}

}
}

public class Plan1571768738672 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("B");
}

} else {
StartServer("A");
}

StartServer("B");

}


}
}

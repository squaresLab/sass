public class Plan1571770238599 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("C");

StartServer("B");

} else {
StartServer("A");
}

if ( StartServer("A") ) {
StartServer("C");
} else {
IncreaseTraffic("C");
}


}

}
}

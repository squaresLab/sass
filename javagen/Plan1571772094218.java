public class Plan1571772094218 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}

StartServer("C");

StartServer("A");

}

if ( DecreaseTraffic("A") ) {
DecreaseTraffic("A");
} else {

}


}
}

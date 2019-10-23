public class Plan1571772540441 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 6 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("B");
}

StartServer("B");

}

if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("B");
}


}
}

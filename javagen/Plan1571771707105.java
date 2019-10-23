public class Plan1571771707105 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("B");

} else {
StartServer("A");
}

} else {
StartServer("A");
}

}

for (int i = 0; i < 3 ; i++) {
StartServer("B");
}



}
}

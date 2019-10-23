public class Plan1571770282570 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("C");

} else {
DecreaseDimmer("A");
}

for (int i = 0; i < 2 ; i++) {
StartServer("A");
}


}


}
}

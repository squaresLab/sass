public class Plan1571773723775 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( IncreaseTraffic("C") ) {
DecreaseTraffic("B");
} else {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

}

} else {
StartServer("B");
}

}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");

}

StartServer("A");

StartServer("B");
DecreaseTraffic("A");



}
}

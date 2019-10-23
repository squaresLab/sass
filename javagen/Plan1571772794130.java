public class Plan1571772794130 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseDimmer("C") ) {

} else {
StartServer("C");
StartServer("A");

}

}

StartServer("B");

if ( DecreaseTraffic("A") ) {
DecreaseDimmer("B");
} else {
StartServer("A");
}

for (int i = 0; i < 5 ; i++) {
StartServer("B");
}



}
}

public class Plan1571768325173 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
StartServer("B");
}

}

if ( StartServer("C") ) {
if ( IncreaseDimmer("B") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
DecreaseDimmer("B");
}

} else {
IncreaseTraffic("C");
}

for (int i = 0; i < 3 ; i++) {
StartServer("A");
}



}
}

public class Plan1571769699364 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
DecreaseDimmer("B");
}

} else {
IncreaseTraffic("C");
}

StartServer("A");

}

if ( StartServer("B") ) {
DecreaseDimmer("C");
} else {
StartServer("A");
}


for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


StartServer("C");

}
}

public class Plan1571774847851 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("B");
}

}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");

}

if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("B");
}

if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}




}
}

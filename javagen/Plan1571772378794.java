public class Plan1571772378794 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

} else {
StartServer("B");
StartServer("C");

}

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
DecreaseTraffic("A");
} else {
IncreaseTraffic("B");
}

}

StartServer("C");
StartServer("B");

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}

StartServer("B");
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}






}
}

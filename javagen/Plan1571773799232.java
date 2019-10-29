public class Plan1571773799232 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("A");
}

}

StartServer("B");

for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



StartServer("B");

StartServer("B");
if ( IncreaseTraffic("B") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}

} else {
if ( StartServer("B") ) {
StartServer("C");
} else {
IncreaseTraffic("C");
}

}



}
}

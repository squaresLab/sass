public class Plan1571773243519 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}


}

if ( StartServer("B") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {
StartServer("B");
}

for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

StartServer("B");



}
}

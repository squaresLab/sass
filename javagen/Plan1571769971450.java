public class Plan1571769971450 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
DecreaseTraffic("A");
}

} else {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

}

for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}

StartServer("C");

StartServer("B");

}


}
}

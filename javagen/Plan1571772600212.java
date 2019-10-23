public class Plan1571772600212 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}

}

if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
StartServer("A");
}


for (int i = 0; i < 4 ; i++) {
StartServer("B");
DecreaseTraffic("A");

}


}
}

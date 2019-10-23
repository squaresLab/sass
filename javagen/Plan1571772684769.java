public class Plan1571772684769 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("A");
}

if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}


}

if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

} else {
StartServer("A");
}


}
}

public class Plan1571768507703 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("C");
}


} else {
IncreaseTraffic("A");
}

for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("C");
}



}

} else {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("C");
}

StartServer("B");

}

}

}
}

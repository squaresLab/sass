public class Plan1571769104039 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

}

if ( StartServer("B") ) {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {
IncreaseTraffic("B");
IncreaseTraffic("B");

}

}

StartServer("A");

} else {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

}


}
}

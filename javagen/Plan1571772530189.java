public class Plan1571772530189 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {
StartServer("C");


}

}

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

StartServer("B");

}


}
}

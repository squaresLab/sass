public class Plan1571772477799 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("C") ) {
for (int i = 0; i < 5 ; i++) {
StartServer("A");
}

StartServer("B");

StartServer("B");

StartServer("A");

if ( StartServer("A") ) {
StartServer("A");
} else {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

}

StartServer("B");


} else {
if ( DecreaseTraffic("C") ) {
if ( StartServer("A") ) {
StartServer("A");
} else {
StartServer("A");
}

StartServer("A");

StartServer("B");

} else {
IncreaseDimmer("B");
}

if ( StartServer("A") ) {
StartServer("B");
StartServer("A");

} else {
if ( StartServer("A") ) {
StartServer("A");
} else {
StartServer("A");
}

}


}

}
}

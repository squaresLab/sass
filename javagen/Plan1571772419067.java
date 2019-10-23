public class Plan1571772419067 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
StartServer("C");

StartServer("B");

}

if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("A");
}


}

} else {
if ( StartServer("B") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}

} else {
DecreaseDimmer("B");
}

}

}
}

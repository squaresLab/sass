public class Plan1571772133598 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}

}

} else {
StartServer("C");
}

DecreaseDimmer("A");

}

} else {
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

}

}

} else {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

}

}
}

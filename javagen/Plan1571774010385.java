public class Plan1571774010385 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 4 ; i++) {
if ( StartServer("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

}

StartServer("C");

}

} else {
if ( IncreaseDimmer("B") ) {
StartServer("A");
} else {
StartServer("B");
}

DecreaseDimmer("B");

}

}
}

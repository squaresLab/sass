public class Plan1571769442888 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseDimmer("B") ) {
StartServer("B");
} else {
if ( StartServer("A") ) {
DecreaseDimmer("B");
} else {
if ( StartServer("A") ) {
IncreaseTraffic("A");
} else {
if ( StartServer("A") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

} else {
DecreaseDimmer("B");
}

}

}

}

}

}
}

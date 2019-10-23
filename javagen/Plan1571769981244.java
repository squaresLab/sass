public class Plan1571769981244 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

if ( StartServer("B") ) {
if ( DecreaseDimmer("B") ) {
DecreaseDimmer("A");
if ( DecreaseTraffic("B") ) {
IncreaseDimmer("B");
} else {
DecreaseDimmer("A");
}


} else {
StartServer("A");
}

} else {
DecreaseDimmer("B");
}


}
}

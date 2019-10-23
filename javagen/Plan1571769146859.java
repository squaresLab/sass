public class Plan1571769146859 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


StartServer("C");

} else {
if ( DecreaseDimmer("C") ) {
ShutdownServer("A");
} else {
if ( DecreaseDimmer("B") ) {
DecreaseDimmer("C");
} else {
IncreaseDimmer("C");
StartServer("A");

}

}

}

}

}
}

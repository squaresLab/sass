public class Plan1571773175104 extends Plan { 
public static void main(String[] args) { 
if ( ShutdownServer("A") ) {
if (  ) {
IncreaseDimmer("B");
for (int i = 0; i < 2 ; i++) {
ShutdownServer("C");
}


} else {
for (int i = 0; i < 2 ; i++) {
if ( DecreaseDimmer("C") ) {
StartServer("A");
} else {
DecreaseTraffic("A");
}

}

}

} else {
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
DecreaseTraffic("A");
}

}

}

for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

}


}
}

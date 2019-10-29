public class Plan1571773588569 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

} else {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

}

for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


StartServer("C");

} else {
IncreaseTraffic("A");
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}


}

}

}
}

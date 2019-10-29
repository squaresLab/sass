public class Plan1571774267384 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}

if ( StartServer("C") ) {
if ( IncreaseTraffic("B") ) {
DecreaseDimmer("A");
} else {
if ( StartServer("B") ) {
DecreaseDimmer("C");
if ( StartServer("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {
IncreaseTraffic("A");
}


} else {
StartServer("B");
}

}

} else {
DecreaseDimmer("C");
}


}
}

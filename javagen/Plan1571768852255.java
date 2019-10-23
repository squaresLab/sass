public class Plan1571768852255 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( DecreaseDimmer("B") ) {
IncreaseDimmer("B");
} else {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
StartServer("C");
StartServer("B");

} else {
StartServer("B");
StartServer("C");

}

}

}

} else {

}

}

for (int i = 0; i < 4 ; i++) {
StartServer("A");
}


}
}

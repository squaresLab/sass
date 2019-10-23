public class Plan1571774570528 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( DecreaseDimmer("C") ) {

} else {
StartServer("C");
}

}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}


for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}

}


}
}

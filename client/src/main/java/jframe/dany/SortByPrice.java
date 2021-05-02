package jframe.dany;

import java.util.Comparator;


class SortByPrice implements Comparator<OneOffer> {
    // Used for sorting in ascending order of
    // roll number
    public int compare(OneOffer a, OneOffer b)
    {
        return Integer.parseInt(a.getPrice()) - Integer.parseInt(b.getPrice());
    }
}

